class SentimentsController < ApiController

  def create
    return unauthorized! unless current_user.present? && current_user.admin?

    sentiment = Sentiment.new(sentiment_params)

    if sentiment.save
      respond(200, "Sentiment successfully created!", SentimentSerializer.new(sentiment).as_json['data']['attributes'])
    else
      bad_request!(sentiment)
    end
  end

  def index
    return unauthorized! unless current_user.present? && current_user.admin?

    sentiments = Sentiment.all
    sentiments = sentiments.paginate(params)

    respond(200, 'Sentiments fetched successfully!', SentimentSerializer.new(sentiments.paginate(params)).as_json['data'].pluck('attributes'))
  end

  def show
    sentiment = Sentiment.find(params[:id])

    if sentiment
      respond(200, 'Sentiment fetched successfully!', SentimentSerializer.new(sentiment).as_json['data']['attributes'])
    else
      not_found!
    end
  end

  private

  def sentiment_params
    params.permit(
      :id,
      :emotion_anger,
      :emotion_disgust,
      :emotion_fear,
      :emotion_joy,
      :emotion_sadness,
      :language_analytical,
      :language_confident,
      :language_tentative,
      :social_openness,
      :social_conscientiousness,
      :social_extraversion,
      :social_agreeableness,
      :social_emotional_range,
      :sentimentable_type,
      :sentimentable_id
    )
  end
end
