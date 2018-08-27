class TagSerializer
  include FastJsonapi::ObjectSerializer

  attributes :id, :value, :enabled, :created_at, :updated_at, :tag_category, :target
end
