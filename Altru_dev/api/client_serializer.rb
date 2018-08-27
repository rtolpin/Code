class ClientSerializer
  include FastJsonapi::ObjectSerializer

  attributes :id, :name, :image_url, :public_page_enabled, :created_at, :updated_at,
  :enabled, :login_code, :pixel_tag, :slug, :terms, :reminder_delay, :manual_transcripts

  attribute :client_tag_ids do |object|
    object.tags.pluck(:id)
  end
end
