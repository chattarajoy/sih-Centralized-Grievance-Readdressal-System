class DropUserTokenFromComplaints < ActiveRecord::Migration[5.0]
  def change
    remove_column :complaints, :user_token
    remove_column :complaints, :user_type
  end
end
