require 'serializers_spec_helper'

RSpec.describe NotificationSerializer, type: :serializer do

  describe 'notification serializer' do
    let(:receiver) { create(:base_user) }
    let(:notification) { create(:notification, receiver: receiver) }

    it 'matches notification_schema' do
      expect(NotificationSerializer.new(notification).as_json['data']['attributes']).to match_json_schema("notification_schema")
    end
  end
end
