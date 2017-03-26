class CreateEmails < ActiveRecord::Migration[5.0]
  def change
    create_table :emails do |t|
      t.string :user_id
      t.string :verify_token
      t.string :user_token

      t.timestamps
    end
  end
end
