class AddAdminUserIdToMunicipalOffice < ActiveRecord::Migration[5.0]
  def change
    add_column :municipal_offices, :admin_user_id, :integer
  end
end
