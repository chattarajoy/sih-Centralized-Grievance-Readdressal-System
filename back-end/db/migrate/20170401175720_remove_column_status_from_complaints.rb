class RemoveColumnStatusFromComplaints < ActiveRecord::Migration[5.0]
  def change
  	remove_column :complaints, :status
  end
end
