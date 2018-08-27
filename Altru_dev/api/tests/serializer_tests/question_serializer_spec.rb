require 'serializers_spec_helper'

RSpec.describe QuestionSerializer, type: :serializer do

  describe 'question serializer' do
    let!(:user){ create(:base_user) }
    let(:question) { create(:question) }

    it 'matches question_schema when passed a user' do
      expect(QuestionSerializer.new(question, {params: {current_user: user}}).as_json['data']['attributes']).to match_json_schema("question_schema")
    end

    it 'matches question_schema when not passed a user' do
      expect(QuestionSerializer.new(question, {params: {current_user: nil}}).as_json['data']['attributes']).to match_json_schema("question_schema")
    end
  end
end
