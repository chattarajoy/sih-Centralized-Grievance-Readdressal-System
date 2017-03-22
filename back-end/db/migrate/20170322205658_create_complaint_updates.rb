class CreateComplaintUpdates < ActiveRecord::Migration[5.0]
  def change
    create_table :complaint_updates do |t|
      t.integer :complaint_id
      t.integer :assigned_to
      t.text :notes

      t.timestamps
    end
  end
end
