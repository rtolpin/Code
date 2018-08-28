class UserSerializer
  include FastJsonapi::ObjectSerializer

  attributes :id, :image_url, :email, :company, :job_title, :tagline, :school,
    :graduation_year, :city, :password_reset_token, :verification_token,
    :password_digest, :authorization_token, :access_level, :last_device_token,
    :last_operating_system, :last_signed_in, :enabled, :created_at, :updated_at,
    :client_id, :player_ids, :is_public, :twitter_handle, :linkedin_handle,
    :facebook_handle, :summary, :allow_push, :allow_email, :is_new, :tag_ids

  attribute :gender do |object|
    object.gender.to_s
  end

  attribute :ethnicity do |object|
    object.ethnicity.to_s
  end

  attribute :first_name do |object|
    object.first_name || ''
  end

  attribute :last_name do |object|
    object.last_name || ''
  end

  attribute :profile_answer_ids, if: Proc.new { |object, params| object.is_public? || (params && params[:current_user] && object.id == params[:current_user][:id]) } do |object|
    object.profile_answers.order(:order).pluck(:answer_id)
  end

  attribute :urls, if: Proc.new { |object, params| object.is_public? || (params && params[:current_user] != nil && object.id == params[:current_user][:id]) } do |object|
    object.urls.as_json
  end
end
