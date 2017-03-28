class ChangeAssignedToToStringInComplaintUpdates < ActiveRecord::Migration[5.0]
  def change
    change_column :complaint_updates, :assigned_to, :string
  end
end
