class AddAadharNumberToSmsOtps < ActiveRecord::Migration[5.0]
  def change
    add_column :sms_otps, :aadhar_number, :string
  end
end
