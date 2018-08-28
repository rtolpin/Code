class QuestionSerializer
  include FastJsonapi::ObjectSerializer

  attributes :id, :asker_id, :text, :hide_asker, :enabled, :created_at, :updated_at,
    :employee_id, :reason_for_rejection, :status, :creator_id, :client_id, :instructions,
    :orientation, :tag_ids

  attribute :total_answers do |object|
    object.try(:total_answers) || 0
  end

  attribute :approved_answers do |object|
    object.try(:approved_answers) || 0
  end

  attribute :assigned_answers do |object|
    object.try(:assigned_answers) || 0
  end

  attribute :pending_approval_answers do |object|
    object.try(:pending_approval_answers) || 0
  end

  attribute :rejected_answers do |object|
    object.try(:rejected_answers) || 0
  end

  attribute :asker do |object|
    object.hide_asker || object.asker.nil? ? {} : {
      id: object.asker.id,
      first_name: object.asker.first_name,
      image_url: object.asker.image_url
    }
  end

  attribute :answers do |object|
    object.answers.where(status: 'approved').map do |a|
      {
        button_href: a.button_href,
        button_label: a.button_label,
        cc_url: a.cc_url,
        cc_urls: a.cc_urls,
        confidence: a.confidence,
        content_type: a.content_type,
        created_at: a.created_at,
        duration: a.duration,
        enabled: a.enabled,
        id: a.id,
        info: a.info,
        question: {},
        question_id: a.question_id,
        reason_for_rejection: a.reason_for_rejection,
        recorded_at: a.recorded_at,
        response_time: a.response_time,
        status: a.status,
        thumbnail_url: a.thumbnail_url,
        transcribed_at: a.transcribed_at,
        transcript: a.transcript,
        transcript_attempts: a.transcript_attempts,
        transcript_order_number: a.transcript_order_number,
        transcript_status: a.transcript_status,
        transcripts: {},
        updated_at: a.updated_at,
        user_id: a.user_id,
        video_height: a.video_height,
        video_url: a.video_url,
        video_width: a.video_width
      }
    end
  end
end
