class Complaint < ApplicationRecord
	has_many :complaint_updates
	validates :subject, presence: true
	validates :description, presence: true
	validates :city, presence: true
	validates :state, presence: true
	validates :pincode, presence: true
	validates :user_id, presence: true
end
