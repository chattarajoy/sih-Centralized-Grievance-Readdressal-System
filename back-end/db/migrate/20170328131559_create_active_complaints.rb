class CreateActiveComplaints < ActiveRecord::Migration[5.0]
  def change
    create_table :active_complaints do |t|
      t.integer :user_id
      t.integer :complaint_id

      t.timestamps
    end
  end
end
