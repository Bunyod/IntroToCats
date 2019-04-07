package milewski.compositions.ch2

object Challenge6 extends App {

  sealed trait OUnit
  sealed trait OVoid
  sealed trait OBool

  case object VUnit extends OUnit
  case object VTrue extends OBool
  case object VFalse extends OBool

  def voidIdentity(v: OVoid): OVoid = v
  def absurdUnit(v: OVoid): OUnit = VUnit
  def absurdTrue(v: OVoid): OBool = VTrue
  def absurdFalse(v: OVoid): OBool = VFalse

  def unitIdentity(v: OUnit): OUnit = v
  def constBottom(v: OUnit): OVoid = ???
  def constTrue(v: OUnit): OBool = VTrue
  def constFalse(v: OUnit): OBool = VFalse

  def boolIdentity(v: OBool): OBool = v

  def negate(v: OBool): OBool = v match {
    case VTrue => VFalse
    case VFalse => VTrue
  }

  val bConstBottom: OBool => OVoid = _ => ???
  val bConstUnit: OBool => OUnit   = _ => VUnit
  val bConstTrue: OBool => OBool   = _ => VTrue
  val bConstFalse: OBool => OBool  = _ => VFalse

}
