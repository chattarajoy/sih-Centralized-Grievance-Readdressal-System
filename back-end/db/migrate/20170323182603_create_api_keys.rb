class CreateApiKeys < ActiveRecord::Migration[5.0]
  def change
    create_table :api_keys do |t|
      t.string :secret_key
      t.integer :user_id

      t.timestamps
    end
  end
end
