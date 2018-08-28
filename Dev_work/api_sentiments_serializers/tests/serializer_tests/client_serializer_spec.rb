require 'serializers_spec_helper'

RSpec.describe ClientSerializer, type: :serializer do

  describe 'client serializer' do
    let(:client) { create(:client) }

    it 'matches client_schema' do
      expect(ClientSerializer.new(client).as_json['data']['attributes']).to match_json_schema("client_schema")
    end
  end
end
