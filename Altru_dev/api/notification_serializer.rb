class NotificationSerializer
  include FastJsonapi::ObjectSerializer

  attributes :id, :sender_id, :sender_id, :receiver_id, :cta_id, :is_seen, :enabled, :created_at,
    :updated_at, :purpose, :text, :mute, :email_sent, :email_message, :receiver, :sender

  attribute :client do |object|
    object.receiver.client
  end

  attribute :subject do |object|
    object.get_subject
  end

  attribute :answers do |object|
    object.answers.map do |a|
      {
        id: a.id,
        created_at: a.created_at,
        duration: a.duration,
        enabled: a.enabled,
        question: a.question.slice(:id, :text),
        question_id: a.question_id,
        recorded_at: a.recorded_at,
        status: a.status,
        thumbnail_url: a.thumbnail_url,
        user_id: a.user_id,
      }
    end
  end
end
