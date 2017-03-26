class Aadhar < ApplicationRecord
  validates_length_of :uid, minimum: 12, maximum: 12, allow_blank: false
  validates_length_of :phone, minimum: 12, maximum: 12, allow_blank: false
  validates_uniqueness_of :uid
end
