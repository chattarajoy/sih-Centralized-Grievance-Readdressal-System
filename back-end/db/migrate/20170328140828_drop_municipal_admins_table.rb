class DropMunicipalAdminsTable < ActiveRecord::Migration[5.0]
  def change
    drop_table :municipal_admins
  end
end
