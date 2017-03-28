class Email < ApplicationRecord
  has_secure_token :verify_token
  has_secure_token :user_token
  validates :user_id, presence: true
end
