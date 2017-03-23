class AdminUser < ApplicationRecord
	has_many :complaint_updates
	has_one :municipal_office

  	has_secure_password

end
