class ComplaintUpdate < ApplicationRecord
	belongs_to :admin_user, foreign_key: "assigned_to"
	belongs_to :complaint
end
