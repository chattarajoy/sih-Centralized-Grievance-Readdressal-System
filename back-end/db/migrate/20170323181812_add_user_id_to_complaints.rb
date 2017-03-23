class AddUserIdToComplaints < ActiveRecord::Migration[5.0]
  def change
    add_column :complaints, :user_id, :integer
  end
end
