class CreateSmsOtps < ActiveRecord::Migration[5.0]
  def change
    create_table :sms_otps do |t|
      t.string :user_id
      t.string :otp

      t.timestamps
    end
  end
end
