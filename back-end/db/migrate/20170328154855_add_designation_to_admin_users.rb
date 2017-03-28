class AddDesignationToAdminUsers < ActiveRecord::Migration[5.0]
  def change
    add_column :admin_users, :designation, :string
  end
end
