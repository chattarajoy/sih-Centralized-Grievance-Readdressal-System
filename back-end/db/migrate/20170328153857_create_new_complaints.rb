class CreateNewComplaints < ActiveRecord::Migration[5.0]
  def change
    create_table :new_complaints do |t|
      t.integer :complaint_id
      t.integer :districtoffice_id

      t.timestamps
    end
  end
end
