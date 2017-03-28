class RemoveMunicipalityIdFromAdminUser1 < ActiveRecord::Migration[5.0]
  def change
    remove_column :admin_users, :municipality_id
  end
end
