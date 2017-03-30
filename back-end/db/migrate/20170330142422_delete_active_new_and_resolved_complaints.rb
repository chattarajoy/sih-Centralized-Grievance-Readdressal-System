class DeleteActiveNewAndResolvedComplaints < ActiveRecord::Migration[5.0]
  def change
    drop_table :resolvedcomplaints
    drop_table :active_complaints
    drop_table :new_complaints
  end
end
