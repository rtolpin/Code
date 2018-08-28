class SentimentSerializer
  include FastJsonapi::ObjectSerializer

  attributes :id, :emotion_anger, :emotion_disgust, :emotion_joy,
    :emotion_fear, :emotion_sadness, :language_analytical, :language_confident,
    :language_tentative, :social_openness, :social_conscientiousness,
    :social_extraversion, :social_agreeableness, :social_emotional_range,
    :sentimentable_type, :sentimentable_id, :created_at, :updated_at
end
