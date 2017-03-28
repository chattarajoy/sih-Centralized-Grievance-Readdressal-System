class AddMunicipalIdToAdminUsers < ActiveRecord::Migration[5.0]
  def change
    add_column :admin_users, :municipal_id, :string
  end
end
