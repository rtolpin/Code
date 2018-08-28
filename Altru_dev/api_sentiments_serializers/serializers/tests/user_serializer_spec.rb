require 'serializers_spec_helper'

RSpec.describe UserSerializer, type: :serializer do

  describe 'user serializer' do
    let(:user) { create(:base_user) }

    it 'matches the user_schema' do
      expect(UserSerializer.new(user).as_json['data']['attributes']).to match_json_schema("user_schema")
    end
  end
end
