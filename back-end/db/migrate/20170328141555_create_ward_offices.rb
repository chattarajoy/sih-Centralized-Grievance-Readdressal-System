class CreateWardOffices < ActiveRecord::Migration[5.0]
  def change
    create_table :ward_offices do |t|
      t.integer :district_office_id
      t.string :ward

      t.timestamps
    end
  end
end
