class AddContactToSmsOtps < ActiveRecord::Migration[5.0]
  def change
    add_column :sms_otps, :contact, :string
  end
end
