class SmsOtp < ApplicationRecord

  validates :otp, presence: true
  validates :user_id, presence: true

end
