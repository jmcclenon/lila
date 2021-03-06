package lila.coach

import org.joda.time.DateTime

import lila.user.User

case class Coach(
    _id: Coach.Id, // user ID
    enabledByUser: Coach.Enabled,
    enabledByMod: Coach.Enabled,
    available: Coach.Available,
    hourlyRate: Option[Coach.Cents],
    profile: CoachProfile,
    picturePath: Option[Coach.PicturePath],
    createdAt: DateTime,
    updatedAt: DateTime) {

  def id = _id

  def is(user: User) = id.value == user.id

  def hasPicture = picturePath.isDefined

  def isFullyEnabled = enabledByUser.value && enabledByMod.value
}

object Coach {

  def make(user: User) = Coach(
    _id = Id(user.id),
    enabledByUser = Enabled(false),
    enabledByMod = Enabled(false),
    available = Available(false),
    hourlyRate = None,
    profile = CoachProfile(),
    picturePath = None,
    createdAt = DateTime.now,
    updatedAt = DateTime.now)

  case class WithUser(coach: Coach, user: User)

  case class Id(value: String) extends AnyVal with StringValue
  case class Enabled(value: Boolean) extends AnyVal
  case class Available(value: Boolean) extends AnyVal
  case class Cents(value: Int) extends AnyVal
  case class PicturePath(value: String) extends AnyVal with StringValue
}
