class CreateMunicipalAdmins < ActiveRecord::Migration[5.0]
  def change
    create_table :municipal_admins do |t|
      t.string :state
      t.string :district

      t.timestamps
    end
  end
end
