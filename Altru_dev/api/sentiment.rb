class Sentiment < ApplicationRecord
  include Paginate

  belongs_to :sentimentable, polymorphic: true
end
