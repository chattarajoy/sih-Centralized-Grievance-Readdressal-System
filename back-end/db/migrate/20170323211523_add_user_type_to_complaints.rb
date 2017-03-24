class AddUserTypeToComplaints < ActiveRecord::Migration[5.0]
  def change
    add_column :complaints, :user_type, :string
  end
end
