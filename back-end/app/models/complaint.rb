class Complaint < ApplicationRecord
  has_many :complaint_updates
  validates :subject, :description, :address, :district, :state, :pincode, :user_id, presence: true
  attr_accessor :status
end
