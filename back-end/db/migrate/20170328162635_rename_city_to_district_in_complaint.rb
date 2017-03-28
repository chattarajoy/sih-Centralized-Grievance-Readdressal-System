class RenameCityToDistrictInComplaint < ActiveRecord::Migration[5.0]
  def change
    rename_column :complaints, :city, :district
  end
end
