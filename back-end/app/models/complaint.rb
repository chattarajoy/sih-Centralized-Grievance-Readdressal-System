class Complaint < ApplicationRecord
	has_many :complaint_updates
	validates :subject, :description, :address, :city, :state, :pincode, :user_id, presence:true
end
