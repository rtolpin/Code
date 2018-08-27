from __future__ import print_function

import os
import requests
import urllib
import json
import time
import httplib
from datetime import datetime, timedelta
import httplib2

login_url = os.environ['altru_api'] + '/users/sessions/'
slack_url = os.environ['slack_webhook']
answers_url = os.environ['altru_api'] + '/answers/'
clients_url = os.environ['altru_api'] + '/clients/'
global file_names
file_names = []

def post_videos():
    try:

        user_auth = { "email": os.environ['admin_email'], "password": os.environ['admin_password'] }
        login = requests.post(login_url, params=user_auth)
        auth_token = login.json()['data']['authorization_token']
        clients = requests.get(clients_url, headers={"Authorization": auth_token})
        last_hour_date_time = datetime.now() - timedelta(30)
        # timedelta(hours = 1)

        for c in clients.json()['data']:
              if c['id'] == 11:
                  continue
              # get all client answers per client id that are less than an hour old and where the status is not equal to assigned
              answer_params = {"client_id": c['id'], 'q[recorded_at_gt]': str(last_hour_date_time), 'q[status_not_eq]': "assigned"}
              client_answers = requests.get(answers_url, params=answer_params, headers={"Authorization": auth_token})
              print(client_answers.text)
              for answer in client_answers.json()['data']:

                  # use url opener to get and download video from url and save file locally
                  url_opener = urllib.FancyURLopener()
                  answer_id = answer['id']
                  file_name = answer['video_url'].split("/")[-1]
                  file_names.append(file_name)
                  media_file = './tmp/' + file_name
                  url_opener.retrieve(answer['video_url'], media_file)
                  if not os.path.exists(media_file):
                      exit('Please specify a valid file location.')

                  answer_user = str(answer['user']['first_name']).encode('utf-8').decode('utf-8') + " " + str(answer['user']['last_name']).encode('utf-8').decode('utf-8')

                  duration = '0:'
                  if answer['duration'] < 10:
                       duration = '0:0'

                  duration += str(answer['duration']).encode('utf-8').decode('utf-8')
                  video_comment = "\n User: " + answer_user + "\n" + "Client: " + c['name'].encode('utf-8').decode('utf-8') + "\n" + "Question: " + answer['question']['text'].encode('utf-8').decode('utf-8')
                  video_comment += "\n Duration: " + duration
                  payload= {"text": video_comment + "<https://app.altrulabs.com/ + c['client_slug'] + "/" + c['feed_slug'] + "?answer_id=" + answer['id'] ">", "unfurl_links": True, "unfurl_media": True}
                  payload = json.dumps(payload)
                  slack_request = requests.post(slack_url, data=payload)
                  print(slack_request.text)



    except Exception as err:
        print(err)
        for file in file_names:
            os.remove('./tmp/' + str(file))

post_videos()
