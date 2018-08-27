require 'rails_helper'

describe 'Sentiments Requests', type: :request do
  let(:client) { create(:client) }
  let(:question) { create(:question, client: client) }
  let(:answer) { create(:answer, question: question) }
  let(:admin_user) { create(:admin_user, client: client) }
  let!(:sentiment) { create(:sentiment, sentimentable: answer) }

  describe 'create' do
    subject(:action) { post 'api/v1/sentiments', params.to_json }
    let(:user) { admin_user }

    before { login_as(user) }

    let(:params) do
      {
        emotion_anger: 52.3,
        emotion_disgust: 23.4,
        sentimentable_type: 'Answer',
        sentimentable_id: answer.id
      }
    end

    context 'when the current user is not an admin' do
      let(:user) { create(:base_user) }

      it 'responds with failure' do
        expect(action).not_to be_ok
      end
    end

    context 'when the current user is an admin' do
      let(:user) { admin_user }

      it 'creates a new sentiment' do
        expect { action }.to change { Sentiment.count }.by(1)
      end

      describe 'sentiment response' do
        subject(:sentiment_response) { JSON.parse(action.body)['data'] }

        it 'includes the id of the answer' do
          expect(sentiment_response).to include('sentimentable_id' => answer.id)
        end

        it 'includes the type of the sentiment object' do
          expect(sentiment_response).to include('sentimentable_type' => 'Answer')
        end

        it 'includes emotion_anger and emotion_disgust' do
          emotion_anger = params[:emotion_anger]
          emotion_disgust = params[:emotion_disgust]
          expect(sentiment_response).to include('emotion_anger' => emotion_anger)
          expect(sentiment_response).to include('emotion_disgust' => emotion_disgust)
        end
      end
  end

  describe 'index' do
    subject(:action) { get 'api/v1/sentiments' }

    let(:user) { admin_user }

    before { login_as(user) }

    it 'responds with success' do
      expect(action).to be_ok
    end
  end

  describe 'show' do
    subject(:action) { get "api/v1/sentiments/#{sentiment.id}" }

    it 'responds with success' do
      expect(action).to be_ok
    end

    describe 'answer response' do
      subject(:answer_response) { JSON.parse(action.body)['data'] }

      it 'returns the answer id in the response' do
        expect(answer_response).to include('id' => sentiment.id)
      end
    end
  end
  end
end
