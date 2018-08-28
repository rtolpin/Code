class AnswerSerializer
  include FastJsonapi::ObjectSerializer

  attributes :id, :user_id, :question_id, :status, :duration, :thumbnail_url, :video_url,
    :enabled, :created_at, :updated_at, :info, :cc_url, :transcribed_at, :confidence,
    :transcript_attempts, :reason_for_rejection, :button_label, :button_href, :cc_urls,
    :response_time, :video_height, :video_width, :recorded_at, :content_type, :transcript_status,
    :transcript_order_number

  attribute :transcripts, if: Proc.new { |object, params| params && params[:current_user] && params[:current_user].admin? }
  attribute :transcript, if: Proc.new { |object, params| params && params[:current_user] && params[:current_user].admin? }

  attribute :question do |object|
    {
      id: object.question.id,
      text: object.question.text,
      created_at: object.question.created_at,
      updated_at: object.question.updated_at,
      instructions: object.question.instructions,
      orientation: object.question.orientation,
      asker: object.question.hide_asker || object.question.asker.nil? ? {} : object.question.asker.attributes.with_indifferent_access.slice(:id, :first_name, :image_url),
      answers: [],
      tag_ids: object.question.tags.ids
    }
  end

  attribute :user do |object|
    object.user == nil ? {} : {
      tag_ids: object.user.tags.ids,
      id: object.user.id,
      first_name: object.user.first_name || '',
      last_name: object.user.last_name || '',
      image_url: object.user.image_url,
      job_title: object.user.job_title
    }
  end

  # attribute :has_liked do |object, params|
  #   params[:current_user].present? ? !object.question.likes.where(user_id: params[:current_user][:id]).empty? : false
  # end

  attribute :likes do |object|
    object.likes.size
  end
end
