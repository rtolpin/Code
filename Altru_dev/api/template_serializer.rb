class TemplateSerializer
  include FastJsonapi::ObjectSerializer

  attributes :id, :client_id, :auth_mode, :logo_url, :cta_text, :cta_href, :social_links_enabled,
    :styles, :created_at, :updated_at, :slug, :template_type, :auth_instructions, :show_title,
    :title, :font, :enabled

  attribute :answer_ids do |object|
    object.answer_ids
  end
end
