class CreatePasswordResetLinks < ActiveRecord::Migration[5.0]
  def change
    create_table :password_reset_links do |t|
      t.integer :user_id
      t.string :access_token
      t.string :secret_key

      t.timestamps
    end
  end
end
