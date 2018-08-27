require 'serializers_spec_helper'

RSpec.describe TemplateSerializer, type: :serializer do

  describe 'template serializer' do
    let(:client) { create(:client) }
    let(:template) { create(:template, client: client) }

    it 'matches template_schema' do
      expect(TemplateSerializer.new(template).as_json['data']['attributes']).to match_json_schema("template_schema")
    end
  end

end
