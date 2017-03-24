class AddUserTokenToComplaints < ActiveRecord::Migration[5.0]
  def change
    add_column :complaints, :user_token, :string
  end
end
