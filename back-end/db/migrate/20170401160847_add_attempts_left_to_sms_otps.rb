class AddAttemptsLeftToSmsOtps < ActiveRecord::Migration[5.0]
  def change
    add_column :sms_otps, :attempts_left, :integer
  end
end
