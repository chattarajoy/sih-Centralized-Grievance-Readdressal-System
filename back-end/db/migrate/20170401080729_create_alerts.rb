class CreateAlerts < ActiveRecord::Migration[5.0]
  def change
    create_table :alerts do |t|
      t.integer :complaint_id
      t.integer :admin_user_id
      t.text :message

      t.timestamps
    end
  end
end
