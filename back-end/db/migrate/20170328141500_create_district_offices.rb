class CreateDistrictOffices < ActiveRecord::Migration[5.0]
  def change
    create_table :district_offices do |t|
      t.string :state
      t.string :district

      t.timestamps
    end
  end
end
