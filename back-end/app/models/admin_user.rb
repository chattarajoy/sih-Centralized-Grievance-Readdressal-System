class AdminUser < ApplicationRecord

	has_many :complaint_updates
	has_one :municipal_office
	before_save { self.email = email.downcase }
	validates :name, presence: true
	VALID_EMAIL_REGEX = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i
	validates :email, presence: true, length: { maximum: 255 },
    	              format: { with: VALID_EMAIL_REGEX },
        	          uniqueness: { case_sensitive: false }
  validates :phone, presence: true
  has_secure_password

end
