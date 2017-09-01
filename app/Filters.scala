/**
  * Created by Donald Pollock on 01/09/2017.
  */
import javax.inject.Inject

import play.api.http.DefaultHttpFilters
import play.filters.cors.CORSFilter

class Filters @Inject()(corsFilter: CORSFilter)
  extends DefaultHttpFilters(corsFilter)
