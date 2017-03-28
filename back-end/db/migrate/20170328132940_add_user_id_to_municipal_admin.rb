class AddUserIdToMunicipalAdmin < ActiveRecord::Migration[5.0]
  def change
    add_column :municipal_admins, :user_id, :integer
  end
end
